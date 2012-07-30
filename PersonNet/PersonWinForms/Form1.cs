using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using PersonNet;
using PersonNet.Formats;

namespace PersonWinForms
{
    public partial class Form1 : Form
    {
        DataBaseNetClient dataBaseNetClient = new DataBaseNetClient(@"csv");

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            lblHiddenId.Visible = false;
            lvPersons.View = View.Details;
            lvPersons.LabelEdit = true;
            lvPersons.AllowColumnReorder = true;
            lvPersons.FullRowSelect = true;
            lvPersons.GridLines = true;

            lvPersons.Columns.Add("Id", "Id");
            lvPersons.Columns.Add("FirstName", "FirstName");
            lvPersons.Columns.Add("LastName", "LastName");
            lvPersons.Columns.Add("Age", "Age");

            lvPersons.ItemSelectionChanged += SetText;

            Update();
        }

        protected void SetText(object obj, ListViewItemSelectionChangedEventArgs args)
        {
            tbFirstName.Text = args.Item.SubItems[1].Text;
            tbLastName.Text = args.Item.SubItems[2].Text;
            tbAge.Text = args.Item.SubItems[3].Text;
            lblHiddenId.Text = args.Item.SubItems[0].Text;
        }

        private void btnInsert_Click(object sender, EventArgs e)
        {
            if (!string.IsNullOrEmpty(tbFirstName.Text) || !string.IsNullOrEmpty(tbLastName.Text) || !string.IsNullOrEmpty(tbAge.Text))
            {
                int age;
                int.TryParse(tbAge.Text, out age);
                Person person = new Person(tbFirstName.Text, tbLastName.Text, Convert.ToInt32(age));
                dataBaseNetClient.Insert(person);
                Update();
                Clear();
            }
        }

        private void btnUpdate_Click(object sender, EventArgs e)
        {
            if (!string.IsNullOrEmpty(lblHiddenId.Text))
            {
                Person person = new Person(tbFirstName.Text, tbLastName.Text, Convert.ToInt32(tbAge.Text));
                person.id = Convert.ToInt32(lblHiddenId.Text);
                dataBaseNetClient.Update(person);
                Update();
            }
        }
        
        private void btnDelete_Click(object sender, EventArgs e)
        {
            if (!string.IsNullOrEmpty(lblHiddenId.Text))
            {
                Person person = new Person(tbFirstName.Text, tbLastName.Text, Convert.ToInt32(tbAge.Text));
                person.id = Convert.ToInt32(lblHiddenId.Text);
                dataBaseNetClient.Delete(person);
                Update();
            }
        }

        private void btnClear_Click(object sender, EventArgs e)
        {
            Clear();
        }

        private void Clear()
        {
            tbFirstName.Text = tbLastName.Text = tbAge.Text = lblHiddenId.Text = string.Empty;
        }

        private new void Update()
        {
            lvPersons.Items.Clear();
            ListPersons listPersons = dataBaseNetClient.Select();

            foreach (Person person in listPersons)
            {
                ListViewItem newItem = new ListViewItem(person.id.ToString());

                newItem.SubItems.Add(person.firstName);
                newItem.SubItems.Add(person.lastName);
                newItem.SubItems.Add(person.age.ToString());

                lvPersons.Items.Add(newItem);
            }

            Controls.Add(lvPersons);
        }
    }
}
