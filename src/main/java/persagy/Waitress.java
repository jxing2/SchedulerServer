package persagy;
/*
 * Author: Jxing
 * Create Time: 2019/3/8
 */

import persagy.pojo.Command;
import persagy.pojo.TaskStatus;
import persagy.utils.CommonUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Waitress implements Runnable {
    public static Map<TaskStatus, Set<Command>> allowedStatusCommand = new ConcurrentHashMap<>();
    static {
        allowedStatusCommand.put(TaskStatus.Waiting, CommonUtil.createSet(Command.TaskSuccess));
        allowedStatusCommand.put(TaskStatus.Sending, CommonUtil.createSet(Command.AcceptTask, Command.RefuseTask, Command.TaskSuccess));
        allowedStatusCommand.put(TaskStatus.Sent, CommonUtil.createSet(Command.CommandError, Command.DownloadError, Command.TaskSuccess));
        allowedStatusCommand.put(TaskStatus.FileDownloadException, CommonUtil.createSet(Command.TaskSuccess));
        allowedStatusCommand.put(TaskStatus.CommandExecuteException, CommonUtil.createSet(Command.TaskSuccess));
        allowedStatusCommand.put(TaskStatus.Finished, CommonUtil.createSet());
    }
    public Socket sock;
    public ObjectOutputStream output;
    public ObjectInputStream input;
    public Waitress(Socket sock) {
        this.sock = sock;
        setupStreams();
        sendMessage("hello from server");
    }

    public boolean sendMessage(String msg){
        try{
            output.writeChars(msg);
            return true;
        }catch (Exception ex){
            return  false;
        }
    }

    private void setupStreams() {
        // TODO Auto-generated method stub
        try {
            output = new ObjectOutputStream(sock.getOutputStream());
            output.flush();
            input = new ObjectInputStream(sock.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            try {
                if(output != null) {
                    output.close();
                }
                if(input != null)
                    input.close();
            } catch (Exception ignore) {
            }
            output = null;
            input = null;
        }
    }



    @Override
    public void run() {

        while(true){

        }
    }
}
