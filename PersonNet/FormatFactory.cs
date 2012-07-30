using System;
using System.Collections.Generic;
using System.Text;
using PersonNet.Formats;

namespace PersonNet
{
    public static class FormatFactory
    {
        private static DataStorage dataStorage;

        static FormatFactory()
        {
            Add(new CSV());
            Add(new DataBase());
            Add(new MOCK());
        }

        private static void Add(DataStorage newDataStorage)
        {
            newDataStorage.next = dataStorage;
            dataStorage = newDataStorage;
        }

        public static DataStorage GetInstance(string fileName)
        {
            return dataStorage.IsReady(fileName);
        }
    }
}
