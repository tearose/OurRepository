using System;
using System.Collections.Generic;
using System.Text;

namespace PersonNet.Formats
{
    public class MOCK : DataStorage
    {
        private ListPersons list = new ListPersons();

        public override DataStorage IsReady(string extension)
        {
            DataStorage dataStorage = null;

            if (extension == "mock")
            {
                this.extension = extension;
                dataStorage = this;
            }
            else if (next != null)
                dataStorage = next.IsReady(extension);

            return dataStorage;
        }

        public override void Insert(Person person)
        {
            int id = GetMaxId() + 1;
            person.id = id;
            list.Clear();
            list.Add(person);
        }

        public override ListPersons Select()
        {
            return list;
        }

        public override void Update(Person person)
        {
            for (int i = 0; i < list.Count; i++)
            {
                if (list[i].id == person.id)
                    list[i] = person;
            }
        }

        public override void Delete(Person person)
        {
            for (int i = 0; i < list.Count; i++)
            {
                if (list[i].id == person.id)
                    list.RemoveAt(i);
            }
        }

        public int GetMaxId()
        {
            int id = 0;
            foreach (Person listPerson in list)
            {
                if (listPerson.id > id)
                    id = listPerson.id;
            }
            return id;
        }
    }
}
