using System;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Text.RegularExpressions;

namespace PersonNet.Formats
{
    public class DataBaseNetClient : DataStorage
    {
        public DataBaseNetClient(string extension)
        {
            this.extension = extension;
        }

        public override DataStorage IsReady(string extension)
        {
            throw new NotImplementedException();
        }

        public override void Insert(Person person)
        {
            Socket socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            socket.Connect(IPAddress.Parse("127.0.0.1"), 3180);

            NetworkStream networkStream = new NetworkStream(socket);

            string dataSend = extension + "|" + "Insert|" + person.ToXML();

            byte[] bytes = Encoding.UTF8.GetBytes(dataSend);
            networkStream.Write(bytes, 0, bytes.Length);

            bool isWork = true;
            while (isWork)
            {
                if (networkStream.DataAvailable)
                {
                    isWork = false;
                    byte[] buffer = new byte[socket.Available];
                    networkStream.Read(buffer, 0, buffer.Length);
                    string result = Encoding.UTF8.GetString(buffer);
                }
            }
            networkStream.Flush();
            networkStream.Close();
            socket.Close();
        }

        public override ListPersons Select()
        {
            ListPersons listPersons = new ListPersons();
            Socket socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.IP);
            socket.Connect(IPAddress.Parse("127.0.0.1"), 3180);
            
            NetworkStream networkStream = new NetworkStream(socket);

            string dataSend = extension + "|" + "Select";

            byte[] bytes = Encoding.UTF8.GetBytes(dataSend);
            networkStream.Write(bytes, 0, bytes.Length);
            networkStream.Flush();

            bool isWork = true;
            string fromServer = string.Empty;
            while(isWork)
            {
                if(networkStream.DataAvailable)
                {
                    isWork = false;
                    byte[] buffer = new byte[socket.Available];
                    networkStream.Read(buffer, 0, buffer.Length);
                    fromServer = Encoding.UTF8.GetString(buffer);
                }
            }
            
            listPersons = ClientServerDataSerializable.Deserialize(fromServer);
            
            networkStream.Close();
            socket.Close();
            return listPersons;
        }

        public override void Update(Person person)
        {
            Socket socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.IP);
            socket.Connect(IPAddress.Parse("127.0.0.1"), 3180);
            NetworkStream networkStream = new NetworkStream(socket);
            string dataSend = extension + "|" + "Update|" + person.ToXML();

            byte[] bytes = Encoding.UTF8.GetBytes(dataSend);
            networkStream.Write(bytes, 0, bytes.Length);

            bool isWork = true;
            while (isWork)
            {
                if (networkStream.DataAvailable)
                {
                    isWork = false;
                    byte[] buffer = new byte[socket.Available];
                    networkStream.Read(buffer, 0, buffer.Length);
                    string result = Encoding.UTF8.GetString(buffer);
                }
            }
            networkStream.Flush();
            networkStream.Close();
            socket.Close();
        }

        public override void Delete(Person person)
        {
            Socket socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.IP);
            socket.Connect(IPAddress.Parse("127.0.0.1"), 3180);
            NetworkStream networkStream = new NetworkStream(socket);
            string dataSend = extension + "|" + "Delete|" + person.ToXML();

            byte[] bytes = Encoding.UTF8.GetBytes(dataSend);
            networkStream.Write(bytes, 0, bytes.Length);

            bool isWork = true;
            while (isWork)
            {
                if (networkStream.DataAvailable)
                {
                    isWork = false;
                    byte[] buffer = new byte[socket.Available];
                    networkStream.Read(buffer, 0, buffer.Length);
                    string result = Encoding.UTF8.GetString(buffer);
                }
            }
            networkStream.Flush();
            networkStream.Close();
            socket.Close();
        }
    }
}
