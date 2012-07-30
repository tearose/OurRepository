using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using NUnit.Framework;
using PersonNet;
using PersonNet.Formats;

namespace TestPersonNet
{
    [TestFixture]
    public class TestPersonNet
    {
        /*[SetUp]
        public void StartServer()
        {
            System.Diagnostics.Process.Start("mspaint.exe");
        }*/
        
        [Test]
        public void TestInsert()
        {
            string fileBaseName = @"D:\DataBasePerson.mock";
            File.Delete(fileBaseName);

            Person person = new Person("Uliana", "Kashirnaya", 23);
            DataBaseNetClient dataBaseNetClient = new DataBaseNetClient("mock");
            dataBaseNetClient.Insert(person);

            ListPersons listPerson = dataBaseNetClient.Select();    

            ListPersons listPersonsExtended = new ListPersons();
            person.id = listPerson[0].id;
            listPersonsExtended.Add(person);

            Assert.IsTrue(listPersonsExtended.Equals(listPerson));
        }

        [Test]
        public void TestSelect()
        {
            string fileBaseName = @"D:\DataBasePerson.mock";
            File.Delete(fileBaseName);

            Person person = new Person("Uliana", "Kashirnaya", 23);
            DataBaseNetClient dataBaseNetClient = new DataBaseNetClient("mock");
            dataBaseNetClient.Insert(person);

            ListPersons listPerson = dataBaseNetClient.Select();

            ListPersons listPersonsExtended = new ListPersons();
            person.id = listPerson[0].id;
            listPersonsExtended.Add(person);

            Assert.IsTrue(listPersonsExtended.Equals(listPerson));
        }

        [Test]
        public void TestUpdate()
        {
            string fileBaseName = @"D:\DataBasePerson.mock";
            File.Delete(fileBaseName);

            Person person = new Person("Uliana", "Kashirnaya", 23);
            DataBaseNetClient dataBaseNetClient = new DataBaseNetClient("mock");
            dataBaseNetClient.Insert(person);

            ListPersons listPerson = dataBaseNetClient.Select();

            ListPersons listPersonsExtended = new ListPersons();
            person.id = listPerson[0].id;
            person.lastName = "Boltyanskaya";
            listPersonsExtended.Add(person);

            dataBaseNetClient.Update(person);
            listPerson = dataBaseNetClient.Select();

            Assert.IsTrue(listPersonsExtended.Equals(listPerson));
        }

        [Test]
        public void TestDelete()
        {
            string fileBaseName = @"D:\DataBasePerson.mock";
            File.Delete(fileBaseName);

            Person person = new Person("Uliana", "Kashirnaya", 23);
            DataBaseNetClient dataBaseNetClient = new DataBaseNetClient("mock");
            dataBaseNetClient.Insert(person);

            ListPersons listPerson = dataBaseNetClient.Select();

            ListPersons listPersonsExtended = new ListPersons();
            person.id = listPerson[0].id;

            dataBaseNetClient.Delete(person);
            listPerson = dataBaseNetClient.Select();

            Assert.IsTrue(listPersonsExtended.Equals(listPerson));
        }
    }
}
