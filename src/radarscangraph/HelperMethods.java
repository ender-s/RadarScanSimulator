package radarscangraph;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HelperMethods 
{
    public static boolean DEBUG = false;
    public static boolean PRINT_MATRIX = true;
    
    public static boolean contains(boolean item, boolean[] list)
    {
        for (boolean i: list) 
            if (i == item) 
                return true;
        
        return false;
    }
    
    public static boolean contains(int item, int[] list)
    {
        for (int i: list)
            if (i == item)
                return true;
        
        return false;
    }
    
    public static boolean contains(Object item, Object[] list)
    {
        for (Object i: list) 
            if (i.equals(item)) 
                return true;
        
        return false;
    }
    
    public static int count(boolean item, boolean[] list)
    {
        int counter = 0;
        for (boolean i: list)
            if (i == item) 
                ++counter;
        
        return counter;
    }
    
    public static int count(Object item, Object[] list)
    {
        int counter = 0;
        for (Object i: list)
            if (i.equals(item)) 
                ++counter;
        
        return counter;
    }     
    
    public static double fromDBToWattsPerMeterSquared(double dB)
    {
        return (1 * Math.pow(10, -12)) * Math.pow(10, dB / 10);
    }
    
    public static double fromWattsPerMeterSquaredToDB(double wattsPerMeterSquared)
    {
        return 10 * Math.log10(wattsPerMeterSquared / (1 * Math.pow(10, -12)));
    }

    public static boolean[] getValidnessList(javax.swing.JTextField[] textFieldList,
            int[] integerFieldIndexes)
    {
        int n = textFieldList.length;
        boolean[] validnessList = new boolean[n];
        double value;
        int val;
        
        for (int i = 0; i < n; ++i)
        {
            validnessList[i] = true;
            
            try
            {
                if (!HelperMethods.contains(i, integerFieldIndexes))
                    value = Double.parseDouble(textFieldList[i].getText());
                else
                    val = Integer.parseInt(textFieldList[i].getText());
            }
            catch(Exception e)
            {
                validnessList[i] = false;
            }
        }
        
        return validnessList;
        
    }
    
    public static boolean checkValidness(boolean[] validnessList, String[] fieldNames, JFrame parentReference)
    {
        if (HelperMethods.contains(false, validnessList))
        {
            String msg = HelperMethods.count(false, validnessList) > 1 
                    ? "Formats of the following fields are incorrect!:" : "Format of the following field is incorrectt!:";
            for (int i = 0; i < validnessList.length; ++i)
                if (!validnessList[i])
                    msg += "\n" + fieldNames[i];
            JOptionPane.showMessageDialog(parentReference, msg, "Invalid input format!", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }

}
