package fd.networ.libs.agent;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.os.Handler;

public class HttpAgent implements Runnable {

	/* production */

	public Handler handler = null;
	private QueueUrl queue;

	public HttpAgent() {
		handler = new Handler();
	}

	public void connect() {
		new Thread(this).start();
	}

	public void disconnect() {

	}

	public void openUrl(HttpListener listener, String url, Object param, int codereq) {

		queue = new QueueUrl(listener, url, param, codereq);

		connect();
	}

	public void run() {
		// TODO Auto-generated method stub

		HttpURLConnection http = null;
		InputStream inputstream = null;
		DataOutputStream outputstream = null;
		ByteArrayOutputStream baos = null;
		int status = 0;
		try {
			int len = 0;
			byte[] out = null;
			if (queue.object instanceof String) {
				String param = (String) queue.object;
				out = param.getBytes();
				len = out.length;
			} else if (queue.object instanceof byte[]) {
				out = ((byte[]) queue.object);
				len = out.length;
			}
			URL myURL = new URL(queue.url);
			http = (HttpURLConnection) myURL.openConnection();
			http.setAllowUserInteraction(true);
			http.setReadTimeout(1000 * 5);
			http.setConnectTimeout(1000 * 5);
			// http.setChunkedStreamingMode(1024);
			http.setDoInput(true);
			http.setDoOutput(true);

			System.out.println("-- HTTP req : " + queue.url);
			System.out.println("-- HTTP par : " + new String(out));

			if (len > 1) {
				http.setRequestMethod("POST");
				http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				http.setRequestProperty("Content-Length", String.valueOf(len));
			} else {
				http.setRequestMethod("GET");
				http.setRequestProperty("Content-Type", "text/plain");
				http.setRequestProperty("Content-Length", String.valueOf(len));
			}

			if (len > 0) {
				outputstream = new DataOutputStream(http.getOutputStream());
				outputstream.write(out);
				outputstream.flush();
				outputstream.close();
				outputstream = null;
			}

			status = http.getResponseCode();

			System.out.println("-- HTTP RESPONSE CODE:" + status);
			if (status == HttpURLConnection.HTTP_OK) {
				inputstream = http.getInputStream();
				parseRespone(inputstream, queue);
			} else if (status == -1) {

			} else {
				inputstream = http.getErrorStream();
				parseRespone(inputstream, queue);
				// String httpResponseMessage = http.getResponseMessage();
				// responseError(queue, httpResponseMessage);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (queue.listener != null) {
				System.out.println("-- http e :" + e.getMessage());
				final String errMsg = "Not Connect To Server";
				responseError(queue, errMsg);
			}
		} finally {
			try {
				if (inputstream != null)
					inputstream.close();
				if (outputstream != null)
					outputstream.close();
				if (http != null)
					http.disconnect();
				if (baos != null)
					baos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			http.disconnect();
			if (status == -1) {
				connect();
			}
		}
	}

	private void parseRespone(InputStream inputstream, final QueueUrl q)
			throws IOException {
		final StringBuffer buf = new StringBuffer();
		int i = 0;
		while ((i = inputstream.read()) != -1) {
			buf.append((char) i);
		}
		// JSONObject.quote(buf.toString());
		// System.out.println("-- HTTP res :"+buf.toString());
		handler.post(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				// Parser.onCreate().parse(q.codeReq, buf.toString(),
				// q.listener);
				q.listener.onHttpResponse(q.codeReq, buf.toString());
			}
		});
		// q.listener.onHttpResponse(0, "");
	}

	private void responseError(final QueueUrl queue, final String message) {
		if (message == null)
			return;
		handler.post(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				if (message.length() > 2)
					queue.listener.onError(-1, message);
			}
		});

	}

	class QueueUrl {
		HttpListener listener;
		String url;
		Object object;
		int codeReq;

		public QueueUrl(HttpListener listener, String url, Object param,
				int code) {
			// TODO Auto-generated constructor stub
			this.listener = listener;
			this.url = url;
			this.object = param;
			this.codeReq = code;
		}
	}

}
