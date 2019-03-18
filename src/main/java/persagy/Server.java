package persagy;
/*
 * Author: Jxing
 * Create Time: 2019/3/12
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import persagy.pojo.Config;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static Logger logger = LoggerFactory.getLogger(Server.class);
    private ServerSocket server;
    private String ip;
    private int port;
    private String mysqlConnStr;
    private ArrayList<Waitress> waitressList ;
    public Server(String ip, int port, String mysqlConnStr) throws IOException {
        this.ip = ip;
        this.port = port;
        this.mysqlConnStr = mysqlConnStr;
        server = new ServerSocket(port, 100);
        waitressList = new ArrayList<>();
    }

    public Server() throws IOException {
        this(new Yaml().loadAs(Server.class.getClassLoader().getResourceAsStream("config.yml"), Config.class));
    }

    private Server(Config config) throws IOException {
        this(config.getIp(), config.getPort(), config.getMysql());
    }

    public void beginServing(){
        while(true){
            try {
                Socket socket = server.accept();
                Waitress waitress = new Waitress(socket);
                waitressList.add(waitress);
                System.out.println("客户端已连接  Client IP: " + socket.getInetAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
