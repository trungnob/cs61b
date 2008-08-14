Debug strategy :
 We mostly use eclipse debuging tools . This is very strong tools for debuging since we can observe all of the instances and variables as well as favor expression. 
 We also implement isOk method to do sanity check on each class .. This can be combined with debug tool to keep track on each of objects was created on each step.
 The "toString" method is the greate way to do visual debug (we call this visual debug mode). The eclipse debuging tool will call this method on every display the instances. So we can literally watch the change of the board on each move as well as which block is moving.
 Thanks for the block ID we can display board nicely and keep track of each move. The Move toString will display "<blockID> <direction of movement>" 

Option :
 we create only one option here to debug the movement of the board 
 We also stop on each board generated in order to see if it is generated correctly or not.