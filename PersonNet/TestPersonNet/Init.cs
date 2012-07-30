using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using PersonNet;

namespace TestPersonNet
{
    public class ListInit
    {
        /*public static ListPersons FillList()
        {
            ListPersons listPersons = new ListPersons();

            listPersons.Add(new Person(1, "Kashirnaya", "Uliana", 23));
            listPersons.Add(new Person(2, "Kashirniy", "Dmitriy", 26));
            listPersons.Add(new Person(3, "Chernenko", "Olga", 72));
            listPersons.Add(new Person(4, "Boltyanskaya", "Inna", 46));
            listPersons.Add(new Person(5, "Boltyanskaya", "Dariya", 18));

            return listPersons;
        }

        public static ListPersons UpdateList(ListPersons listPersons)
        {
            int index = 5;
            foreach (Person person in listPersons)
            {
                person.age = index++;
            }
            return listPersons;
        }

        public static ListPersons DeleteFromList(ListPersons listPersons, out ListPersons listPersonsDelete)
        {
            listPersonsDelete = new ListPersons();
            if (listPersons.Count > 0)
            {
                listPersonsDelete.Add(listPersons[listPersons.Count / 2]);
                listPersons.RemoveAt(listPersons.Count / 2);
            }
            return listPersons;
        }*/

        /*public class MyClass : OnMethodBoundaryAspect
        {
            public override void OnEntry(MethodExecutionArgs args)
            {
                
            }

            public override void OnExit(MethodExecutionArgs args)
            {
                
            }
        }

        public class gg
        {
            [MyClass()]
            private static void SayHello()
            {
                Console.WriteLine("Hello, world.");
            }

            [MyClass()]
            private static void SayGoodBye()
            {
                Console.WriteLine("Good bye, world.");
            }
        }*/
    }
}