package shell;

import util.JsonUtil;
import util.SystemDateFormat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 负责接收客户端的JSON信息，并进行响应。
 */
public class ServerSystem extends Thread {

    private ServerSocket serverSocket;
    private ScheduledExecutorService scheduledExecutorService;

    @Override
    public void run() {
        Thread.currentThread().setName("server core");
        try {
            serverSocket = new ServerSocket(10086);
        } catch (IOException e) {
            e.printStackTrace();
        }
        scheduledExecutorService = Executors.newScheduledThreadPool(8);
        serverStart();
    }

    /**
     * @Description: 循环监听响应，如果有响应，线程池分出一个线程前去处理
     * @param []
     * @Return void
     */
    private void serverStart() {
        System.out.println("服务器已启动......");
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                if (socket != null) {
                    System.out.println(SystemDateFormat.getSystemPreciseDate() + "  新的连接来自 : " + socket.getLocalAddress() + "————" + socket.getLocalAddress().getHostName());
                    newConnection(socket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Description: 线程处理此次响应，并将处理结果返回至客户端
     * @param [socket]
     * @Return void
     */
    private void newConnection(Socket socket) {
        scheduledExecutorService.schedule(() -> {
        //new Thread(() -> {
            ScanController scanController = new ScanController();
            BufferedReader bufferedReader = null;
            BufferedWriter bufferedWriter = null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                String tempMessage;
                StringBuilder message = new StringBuilder();
                while ((tempMessage = bufferedReader.readLine()) != null) {
                    message.append(tempMessage);
                }
                bufferedWriter.write(JsonUtil.toJson(scanController.scan(JsonUtil.analysis(message.toString()))));
                bufferedWriter.flush();
                //socket.shutdownInput();
                socket.shutdownOutput();
            } catch (IOException e) {
                //e.printStackTrace();
            } finally {
                try {
                    if (bufferedReader != null && bufferedWriter != null) {
                        bufferedReader.close();
                        bufferedWriter.close();
                    }
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        //}).start();
        },0, TimeUnit.SECONDS);
    }

}
