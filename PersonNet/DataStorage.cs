using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace PersonNet
{
    public abstract class DataStorage
    {
        protected string extension;
        public DataStorage next;

        public abstract DataStorage IsReady(string extension);

        public abstract void Insert(Person listPersons);
        public abstract ListPersons Select();
        public abstract void Update(Person listPersons);
        public abstract void Delete(Person listPersons);
    }
}
