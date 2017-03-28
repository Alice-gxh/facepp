package com.ikohoo.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class TransactionManager {
	private TransactionManager() {
	}
	//--����Դ,���������ж�ֻ����һ������Դ
	private static DataSource source = new ComboPooledDataSource();
	
	//--�Ƿ�������ı��
	private static ThreadLocal<Boolean> isTran_local = new ThreadLocal<Boolean>(){
		@Override
		protected Boolean initialValue() {
			return false;//--�ʼfalse,����Ĭ�ϲ���������
		}
	};
	//--������ʵ���ӵĴ�������,�����close����
	private static ThreadLocal<Connection> proxyConn_local = new ThreadLocal<Connection>(){};
	//--������ʵ����
	private static ThreadLocal<Connection> realconn_local = new ThreadLocal<Connection>(){};

	/**
	 * ��������ķ���
	 * @throws SQLException
	 */
	public static void startTran() throws SQLException{
		isTran_local.set(true);//--����������Ϊtrue
		final Connection conn = source.getConnection();//--��������,���е�ǰ�߳��е����ݿ�������������conn
		conn.setAutoCommit(false);//--��������
		realconn_local.set(conn);//--Ϊ�˷�������ر�����,��������ӱ������ڵ�ǰ�߳���
		
		//--����һ��������Ҫִ�ж���sql,ÿ��sqlִ�й��󶼹ر�����,����һ��������sqlû��ִ��,��������ط�������close����,ʹ�����ܹر�����
		Connection proxyConn = (Connection) Proxy.newProxyInstance(conn.getClass().getClassLoader(), conn.getClass().getInterfaces()
			, new InvocationHandler(){

				public Object invoke(Object proxy, Method method,
						Object[] args) throws Throwable {
					if("close".equals(method.getName())){
						return null;
					}else{
						return method.invoke(conn, args);
					}
				}
			
		});
		
		proxyConn_local.set(proxyConn);
	}
	
	/**
	 * �ύ����
	 */
	public static void commit(){
		DbUtils.commitAndCloseQuietly(proxyConn_local.get());
	}
	
	/**
	 * �ع�����
	 */
	public static void rollback(){
		DbUtils.rollbackAndCloseQuietly(proxyConn_local.get());
	}
	
	/**
	 * �������Ӧ������:
	 * 		���û�п���������,�򷵻�����ͨ������Դ
	 * 		�������������,�򷵻�һ�������getConnection����������Դ,������������ÿ�ζ�����ͬһ�������������Connection
	 * @return
	 * @throws SQLException 
	 */
	public static DataSource getSource() throws SQLException{
		if(isTran_local.get()){//--�������������,�򷵻ظ����DataSource,����Ϊÿ�ε���getConnection������ͬһ�������������Conn
			
			
			return (DataSource) Proxy.newProxyInstance(source.getClass().getClassLoader(), source.getClass().getInterfaces()
				,new InvocationHandler(){
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						if("getConnection".equals(method.getName())){
							return proxyConn_local.get();
						}else{
							return method.invoke(source, args);
						}
					}
			});
			
		}else{//--û�п���������,������ͨ������Դ
			return source;
		}
		
	}
	
	/**
	 * �ͷ���Դ 
	 */
	public static void release(){
		DbUtils.closeQuietly(realconn_local.get());//--֮ǰ������û�йرյ���release��ʱ�������Ĺر�����
		realconn_local.remove();
		proxyConn_local.remove();
		isTran_local.remove();
	}
	
}

