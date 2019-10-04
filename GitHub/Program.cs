using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GitHub
{
    class Program
    {
        static void Main(string[] args)
        {
            //-- megoldasom----
            List<int> szamok = new List<int>();
            Console.WriteLine("Adja meg hány darab számotr szeretne megadni!");
            int n = Convert.ToInt32(Console.ReadLine());

            Console.WriteLine("Adjon meg a számokat!");
            for (int i = 0; i < n; i++)
            {

            
            int szam = Convert.ToInt32(Console.ReadLine());
            szamok.Add(szam);
            }

            int max = szamok[0];
            for (int i = 0; i < n; i++)
                if (szamok[i] > max)
                    max = szamok[i];

            Console.WriteLine("A legnagyobb elem: {0}", max);

            int min;

            
            min = szamok[0];
            for (int i = 1; i < n; i++)
                if (szamok[i] < min)
                    min = szamok[i];

            Console.WriteLine("A legkisebb elem: {0}", min);


            Console.ReadLine();
        }
    }
}
