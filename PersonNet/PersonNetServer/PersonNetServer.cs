using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;

namespace PersonNetServer
{
    public class PersonNetServer
    {
        private TcpListener tcpListener;

        public PersonNetServer()
        {
            tcpListener = new TcpListener(IPAddress.Parse("127.0.0.1"), 3180);
            tcpListener.Start();
            Console.WriteLine("Listening...");
            Listen();
        }

        public void Listen()
        {
            while (true)
            {
                new Client(tcpListener.AcceptSocket());
            }
        }
    }
}
