package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {

    private TcpConnection connection;

    public Disconnected(TcpConnection connection) {
        this.connection = connection;
    }
    @Override
    public void connect() {
        connection.setState(new Connected(connection));
    }

    @Override
    public void disconnect() {
        System.out.println("Error! Connection already disconnected");
    }

    @Override
    public void write() {
        System.out.println("Error! There is no connection");
    }

    @Override
    public String status() {
        return "disconnected";
    }
}
// END
