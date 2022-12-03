package exercise.connections;

import exercise.TcpConnection;

public interface Connection {
    // BEGIN
    void connect();
    void disconnect();
    void write();
    String status();
    // END
}
