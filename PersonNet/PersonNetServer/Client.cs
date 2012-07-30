using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using PersonNet;

namespace PersonNetServer
{
    public class Client
    {
        private Socket socket;
        private DataStorage dataStorage;

        public Client(Socket socket)
        {
            this.socket = socket;
            Thread interact = new Thread(SendToClient);
            interact.Start();
        }

        /*public void SendToClient()
        {
            
                using (Socket client = socket) //Присоеденился клиент
                using (NetworkStream stream = new NetworkStream(client)) //Берем поток для текущего клиента
                using (StreamReader reader = new StreamReader(stream)) //Reader для чтения из потока (получение)
                using (StreamWriter writer = new StreamWriter(stream)) //Writer для записи в поток (отправки)
                {

                    string clientHandshake = String.Empty; //Переменная для чтения клиентского handshake
                    string currentRead = null; //Текущая прочитанная строка
                    string clientOrigin = ""; //Определяем откуда к нам поступил запрос, для включения в ответ

                    currentRead = reader.ReadToEnd();
                    Console.WriteLine(currentRead);

                    string stringBuilder = "HTTP/1.1 101 Web Socket Protocol Handshake\r\n";
                    stringBuilder += "Upgrade: WebSocket\r\n";
                    stringBuilder += "Connection: Upgrade\r\n";

                    stringBuilder += String.Format("Origin: {0}", "http://localhost:8080/\r\n");
                    stringBuilder += "WebSocket-Location: ws://localhost:3180/";

                    writer.Write(stringBuilder);
                    //writer.Flush();
                    Console.WriteLine("\r\n\r\n\r\n" + stringBuilder);
                    
                    //Даем клиенту/браузеру время сообразить.
                    //Thread.Sleep(100);

                    //Специальные байты которые начинают и заканчивают сообщения
                    byte[] first = new byte[] {0x00};
                    byte[] last = new byte[] {0xFF};

                    //Отсылаем первый байт (начинаем сообщение)                   
                    client.Send(first);

                    //Ну и куда же без Hello World
                    client.Send(Encoding.UTF8.GetBytes("Hello world!"));

                    //Отсылаем последний байт (заканчиваем сообщение)
                    client.Send(last);

                }
           
        }*/

        public void SendToClient()
        {
            if (socket.Available > 0)
            {
                Person person = new Person();
                string fileName = string.Empty;
                string action = string.Empty;

                byte[] bytesFrom = new byte[socket.Available];

                socket.Receive(bytesFrom, 0);
                string data = Encoding.UTF8.GetString(bytesFrom);
                Console.WriteLine(data);
                string[] datas = data.Split('|');

                fileName = datas[0];
                action = datas[1];
                if (action != "Select")
                {
                    person.FromXML(datas[2]);
                }

                dataStorage = FormatFactory.GetInstance(fileName);
                string strResult = "Ok";

                switch (action)
                {
                    case "Insert":
                        dataStorage.Insert(person);
                        break;
                    case "Select":
                        ListPersons listPersons = dataStorage.Select();
                        strResult = ClientServerDataSerializable.Serialize(listPersons);
                        if (string.IsNullOrEmpty(strResult))
                            strResult = "Empty";
                        break;
                    case "Update":
                        dataStorage.Update(person);
                        break;
                    case "Delete":
                        dataStorage.Delete(person);
                        break;
                    default:
                        break;
                }
                
                Console.WriteLine(strResult);
                byte[] bytesTo = Encoding.UTF8.GetBytes(strResult);
                socket.Send(bytesTo, 0);
                socket.Close();
            }
        }
    }
}
