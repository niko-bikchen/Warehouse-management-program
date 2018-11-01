# Warehouse-management-program
This is a warehouse management program which can read information about goods and groups of goods from files, edit it and than save modified data to files.
## Implemented functions:
1. Graphical user interface

2. Ability to save data to file / files

3. Exclided the possibility to create groups of goods / goods with the same names

4. Ability to add / edit / delete groups of goods - when you delete a group of goods, all the goods of the group are deleted as well.

5. Ability to add / edit / delete goods in a group of goods

6. Implemented the interface for adding goods, the interface for writing off goods

7. Implemented the search for a product.

8. Output of statistical data: the output of all goods with information about them, the output of all goods in the group of goods with information about them, the total value of goods in the warehouse, the total value of goods in the group of goods.
## Program structure: 
The program is divided into several windows. The windows in which the lists of groups of goods and goods of a certain group of goods are displated considered the main. Other windows (error windows, alerts, windows that are called for adding, editing, etc. goods / groups of goods, statistics windows, search windows, etc.) are considered as auxiliary.
## Input files requirements: 
Extensions for files so the program could read them - .txt
In a file containing information about groups of goods, information about each group of goods should be separated by
the symbol ",". Each line in this file should correspond to one group of goods.


**Example:**


Furniture (Name), Some furniture here(Description)
Sweets, Some sweets here
 
 
For the file containing indormation about goods the conditions are the same. It is necessary to add at the beginnig of the product description the name of group of goods it belongs to.


**Example:**


Sweets (Goods Group), Candy (Name), This is Candy (Description), Confectionery (Manufacturer Name), 15 (Quantity), 23.50 (Price).
