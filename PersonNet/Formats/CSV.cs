using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using PersonNet;

namespace PersonNet.Formats
{
    public class CSV : DataStorage
    {
        private string fileName = @"DataBasePerson.csv";
        private string fileNameTemp = @"DataBasePerson_temp.csv";

        public override DataStorage IsReady(string extension)
        {
            DataStorage dataStorage = null;

            if (extension == "csv")
            {
                this.extension = extension;
                dataStorage = this;
            }
            else if (next != null)
                dataStorage = next.IsReady(extension);

            return dataStorage;
        }

        private void FileControl()
        {
            if (!File.Exists(fileName))
            {
                FileStream fileStream = File.Create(fileName);
                fileStream.Close();
            }
        }

        public override void Insert(Person person)
        {
            string str;
            int maxId = 1;
            FileControl();

            StreamReader streamReader = new StreamReader(fileName);
            while ((str = streamReader.ReadLine()) != null)
            {
                int idRead;
                int.TryParse(str.Split(',')[0], out idRead);

                if (idRead > maxId)
                    maxId = idRead;
            }
            streamReader.Close();

            StreamWriter streamWriter = new StreamWriter(fileName, true);
            person.id = maxId + 1;
            streamWriter.WriteLine(person.ToCSV());
            streamWriter.Close();
        }

        public override ListPersons Select()
        {
            FileControl();
            string str = string.Empty;
            ListPersons listPersons = new ListPersons();
            StreamReader streamReader = new StreamReader(fileName);
            
            while ((str = streamReader.ReadLine()) != null)
            {
                Person person = new Person();
                person.FromCSV(str);
                listPersons.Add(person);
            }
            streamReader.Close();

            return listPersons;
        }

        public override void Update(Person person)
        {
            string str;
            FileControl();
            StreamReader streamReader = new StreamReader(fileName);
            StreamWriter streamWriter = new StreamWriter(fileNameTemp);

            while ((str = streamReader.ReadLine()) != null)
            {
                int idRead;
                int.TryParse(str.Split(',')[0], out idRead);

                if (idRead == person.id)
                    streamWriter.WriteLine(person.ToCSV());
                else
                    streamWriter.WriteLine(str);
            }
            streamReader.Close();
            streamWriter.Close();

            File.Delete(fileName);
            File.Move(fileNameTemp, fileName);
        }

        public override void Delete(Person person)
        {
            string str;
            FileControl();
            StreamReader streamReader = new StreamReader(fileName);
            StreamWriter streamWriter = new StreamWriter(fileNameTemp);

            while ((str = streamReader.ReadLine()) != null)
            {
                int idRead;
                int.TryParse(str.Split(',')[0], out idRead);

                if (idRead != person.id)
                {
                    streamWriter.WriteLine(str);
                }
            }
            streamReader.Close();
            streamWriter.Close();

            File.Delete(fileName);
            File.Move(fileNameTemp, fileName);
        }
    }
}
