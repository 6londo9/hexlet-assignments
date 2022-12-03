package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {

    private TcpConnection connection;

    public Connected(TcpConnection connection) {
        this.connection = connection;
    }

    @Override
    public void connect() {
        System.out.println("Error! Connection already exists");
    }

    @Override
    public void disconnect() {
        connection.setState(new Disconnected(connection));
    }

    @Override
    public void write() {

    }

    @Override
    public String status() {
        return "connected";
    }
}
// END
