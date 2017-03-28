package ikohoo;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpUriRequest;

public class MyFace {

	public void testHttpClient() {
		String uri = "http://192.168.0.110:8080/users";
		System.out.println(uri);
		try {
			CloseableHttpClient client = HttpClientBuilder.create().build();
			CloseableHttpResponse resp = client.execute(new HttpGet(uri));

			System.out.println("response code: "
					+ resp.getStatusLine().getStatusCode());
			System.out.println("content  type: "
					+ ContentType.getOrDefault(resp.getEntity()).getMimeType());

			System.out.println("response body: "
					+ EntityUtils.toString(resp.getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getUsers() {
		String uri = "http://192.168.0.110:8080/users";
		System.out.println(uri);
		CloseableHttpClient client = HttpClientBuilder.create().build();
		try {

			CloseableHttpResponse resp = client.execute(new HttpGet(uri));

			System.out.println("response code: "
					+ resp.getStatusLine().getStatusCode());
			System.out.println("content  type: "
					+ ContentType.getOrDefault(resp.getEntity()).getMimeType());

			String entity = EntityUtils.toString(resp.getEntity());
			System.out.println("response body: "
					+ entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				client.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return "";
	}

	public String getNodes() {
		String uri = "http://192.168.0.110:8080/nodes";
		CloseableHttpClient client = HttpClientBuilder.create().build();
		try {
			
			CloseableHttpResponse resp = client.execute(new HttpGet(uri));

			System.out.println("response code: "
					+ resp.getStatusLine().getStatusCode());
			System.out.println("content  type: "
					+ ContentType.getOrDefault(resp.getEntity()).getMimeType());

			String entity = EntityUtils.toString(resp.getEntity());
			System.out.println("response body: "
					+ entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				client.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	public static void main(String[] args) {
		MyFace test = new MyFace();
		System.out.println("get users..............");
		test.getUsers();
		System.out.println("get nodes..............");
		test.getNodes();
	}
}
