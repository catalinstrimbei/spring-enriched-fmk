/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.client.ui;

/**
 *
 * @author catalin.strimbei
 */
import javax.swing.JOptionPane;

public class ManyOptions {
  public static void main(String[] args) {

      /*
      Object[] possibilities = {"ham", "spam", "yam"};
String s = (String)JOptionPane.showInputDialog(
                    null,
                    "Complete the sentence:",
                    "Customized Dialog",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[] {"ham", "spam", "yam"},
                    "ham");

    JOptionPane.showInputDialog(
                null,
                "Please choose a name",
                "Example 1",
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[] { "Amanda",
                    "Colin", "Don", "Fred", "Gordon", "Janet", "Jay",
                    "Joe", "Judie", "Kerstin", "Lotus", "Maciek", "Mark",
                    "Mike", "Mulhern", "Oliver", "Peter", "Quaxo", "Rita",
                    "Sandro", "Tim", "Will" },
                "Joe")
       *
*/
    JOptionPane.showInputDialog(
                null,
                "Please choose a name",
                "Example 1",
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[] {"Amanda",
                    "Colin", "Don", "Fred", "Gordon", "Janet", "Jay",
                    "Joe", "Judie", "Kerstin", "Lotus", "Maciek", "Mark",
                    "Mike", "Mulhern", "Oliver", "Peter", "Quaxo", "Rita", "dsadsa"
                    //"Sandro", "Tim", "Will"
                    },
                "Joe");
  }
}
