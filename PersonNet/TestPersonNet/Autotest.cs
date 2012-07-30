using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using NUnit.Framework;
using NUnit.Extensions.Forms;

namespace WindowsFormsApplication1
{
    [TestFixture]
   public class Autotest : NUnitFormTest
    {
        /*private ListViewTester listViewTester;
        //static Form1 form = null;
        static ControlTester TextBox = null;
        static ButtonTester btn1 = null;
        static ButtonTester btn2 = null;
        static ButtonTester btn3 = null;
        static ButtonTester btn4 = null;
        static ButtonTester btn5 = null;
        static ButtonTester btn6 = null;
        static ButtonTester btn7 = null;
        static ButtonTester btn8 = null;
        static ButtonTester btn9 = null;
        static ButtonTester btn0 = null;
        static ButtonTester btnM = null;
        static ButtonTester btnS = null;
        static ButtonTester btnP = null;
        static ButtonTester btnD = null;
        static ButtonTester btnMul = null;

        [SetUp]
        public void Initialize()
        {
            form = new Form1();
            form.Show();
            TextBox = new ControlTester("AddW");
            btn1 = new ButtonTester("1");
            btn2 = new ButtonTester("2");
            btn3 = new ButtonTester("3");
            btn4 = new ButtonTester("4");
            btn5 = new ButtonTester("5");
            btn6 = new ButtonTester("6");
            btn7 = new ButtonTester("7");
            btn8 = new ButtonTester("8");
            btn9 = new ButtonTester("9");
            btn0 = new ButtonTester("0");
            btnM = new ButtonTester("-");
            btnS = new ButtonTester("=");
            btnP = new ButtonTester("+");
            btnD = new ButtonTester("/");
            btnMul = new ButtonTester("*");
        }

        [TearDown]
        public void Cleanup()
        {
            //form.Dispose();
        }
       
        [Test]
        public void Plus()
        {            
            listViewTester.Items.
            btn9.Click();
            btnP.Click();
            btn6.Click();
            btnS.Click();          
            int expected = 15;
            string txt = TextBox["Text"].ToString();
            int actual = Convert.ToInt32(txt);
            Assert.AreEqual(expected, actual);
        }
        [Test]
        public void Min()
        {
            btn9.Click();
            btnM.Click();
            btn6.Click();
            btnS.Click();            
            int expected = 3;
            string txt = TextBox["Text"].ToString();
            int actual = Convert.ToInt32(txt);
            Assert.AreEqual(expected, actual);
        }
        [Test]
        public void Multiplie()
        {           
            btn5.Click();
            btnMul.Click();
            btn6.Click();
            btnS.Click();
            int expected = 30;
            string txt = TextBox["Text"].ToString();
            int actual = Convert.ToInt32(txt);
            Assert.AreEqual(expected, actual);        
        }
        [Test]
        public void Divide()
        {           
            btn9.Click();
            btnD.Click();
            btn3.Click();
            btnS.Click();           
            int expected = 3;
            string txt = TextBox["Text"].ToString();
            int actual = Convert.ToInt32(txt);
            Assert.AreEqual(expected, actual);
        }     
        */
    }
}