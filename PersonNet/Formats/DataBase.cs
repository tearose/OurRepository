using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Text;

namespace PersonNet.Formats
{
    public class DataBase : DataStorage
    {
        public override DataStorage IsReady(string extension)
        {
            DataStorage dataStorage = null;

            if (extension == "mdf")
            {
                this.extension = "Data Source=TEAROSE-NB;Initial Catalog=Person;Integrated Security=True";
                dataStorage = this;
            }
            else if (next != null)
                dataStorage = next.IsReady(extension);

            return dataStorage;
        }

        public override void Insert(Person person)
        {
            SqlConnection connection = new SqlConnection(extension);
            connection.Open();

            string queryString = Person.InsertQuery;
            SqlCommand sqlCommand = new SqlCommand(queryString, connection);
            sqlCommand.Parameters.AddRange(new[] { new SqlParameter("@FirstName", person.firstName),
                                                   new SqlParameter("@LastName", person.lastName),
                                                   new SqlParameter("@Age", person.age) });
            sqlCommand.ExecuteNonQuery();
            connection.Close();
        }

        public override ListPersons Select()
        {
            SqlCommand sqlCommand;
            ListPersons listPersons = new ListPersons();
            SqlConnection connection = new SqlConnection(extension);
            connection.Open();

            sqlCommand = new SqlCommand(Person.SelectQuery, connection);
            SqlDataReader myReader = sqlCommand.ExecuteReader();

            while (myReader.Read())
            {
                object[] objects = new object[myReader.FieldCount];
                myReader.GetValues(objects);
                Person person = new Person();
                person.GetData(objects);
                listPersons.Add(person);
            }
            connection.Close();

            return listPersons;
        }

        public override void Update(Person person)
        {
            SqlConnection connection = new SqlConnection(extension);
            connection.Open();
            string queryString = Person.UpdateQuery;
            SqlCommand sqlCommand = new SqlCommand(queryString, connection);
            sqlCommand.Parameters.AddRange(new[] { new SqlParameter("@Id", person.id),
                                                   new SqlParameter("@FirstName", person.firstName),
                                                   new SqlParameter("@LastName", person.lastName),
                                                   new SqlParameter("@Age", person.age) });
            sqlCommand.ExecuteNonQuery();
            connection.Close();
        }

        public override void Delete(Person person)
        {
            SqlConnection connection = new SqlConnection(extension);
            connection.Open();

            SqlCommand sqlCommand = new SqlCommand(Person.DeleteQuery, connection);
            sqlCommand.Parameters.Add(new SqlParameter("@Id", person.id));
            sqlCommand.ExecuteNonQuery();
            connection.Close();
        }
    }
}
