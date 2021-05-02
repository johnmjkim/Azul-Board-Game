## Code Review

Reviewed by: Si Bo, Hu (u7271125)

Reviewing code written by: Min Jae, Kim (u7269158)

Component: 
[Bag](https://gitlab.cecs.anu.edu.au/u7269158/comp1110-ass2-tue12i/-/blob/master/src/comp1110/ass2/Bag.java)
[Discard](https://gitlab.cecs.anu.edu.au/u7269158/comp1110-ass2-tue12i/-/blob/master/src/comp1110/ass2/Discard.java)
[BagTyped](https://gitlab.cecs.anu.edu.au/u7269158/comp1110-ass2-tue12i/-/blob/master/src/comp1110/ass2/BagTyped.java)
[Factory](https://gitlab.cecs.anu.edu.au/u7269158/comp1110-ass2-tue12i/-/blob/master/src/comp1110/ass2/Factory.java)
[Floor](https://gitlab.cecs.anu.edu.au/u7269158/comp1110-ass2-tue12i/-/blob/master/src/comp1110/ass2/Floor.java)
[Center](https://gitlab.cecs.anu.edu.au/u7269158/comp1110-ass2-tue12i/-/blob/master/src/comp1110/ass2/Center.java)
[OrderTyped](https://gitlab.cecs.anu.edu.au/u7269158/comp1110-ass2-tue12i/-/blob/master/src/comp1110/ass2/OrderTyped.java)
[Mosaic](https://gitlab.cecs.anu.edu.au/u7269158/comp1110-ass2-tue12i/-/blob/master/src/comp1110/ass2/Mosaic.java)
[Storage](https://gitlab.cecs.anu.edu.au/u7269158/comp1110-ass2-tue12i/-/blob/master/src/comp1110/ass2/Storage.java)

### Comments 

Comment:

I give the comments about classes which all places of the tiles being put.
That is a significant part of the game.
The places include Bag, Discard, Factory, Floor, center, Mosaic and Storage.

1.Comment about the structure

There are 4 kinds of the places which were divided by the type of the strings.

  1.1
  -Such as the Bag and Discard have the same string type like one alphabet followed 5 groups of numbers.
  -And the two extends BagTypedObject includes many works related to tiles moving.
  -Class BagTypedObject implements BagTyped and BagTyped extends Tiles.

  1.2
  -Factory, Floor, center also have the same string type.

  1.3 
  -Mosaic and Storage have their own string types. So the two classes are put individually.

This structure is clear and appropriate.

2.Comment about the code itself

2.1
-Methods and variables of the codes are all properly named and style consistent throughout.

2.2
-Some advice: if there can be some annotations about the codes in most classes, it will be easier to understand.
