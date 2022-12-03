package exercise;

import exercise.connections.Connection;
import exercise.connections.Disconnected;

// BEGIN
public class TcpConnection {
    private final String ip;
    private final int port;
    private Connection state;

    public TcpConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.state = new Disconnected(this);
    }

    public String getCurrentState() {
        return state.status();
    }

    public void connect() {
        state.connect();
    }

    public void disconnect() {
        state.disconnect();
    }

    public void write(String data) {
        state.write();
    }

    public void setState(Connection state) {
        this.state = state;
    }
}
// END
