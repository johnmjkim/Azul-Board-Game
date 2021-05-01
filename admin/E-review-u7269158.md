## Code Review

Reviewed by: Min Jae, Kim (u7269158)

Reviewing code written by: Si Bo, Hu (u7271125)

Component: [Viewer](https://gitlab.cecs.anu.edu.au/u7269158/comp1110-ass2-tue12i/-/blob/master/src/comp1110/ass2/gui/Viewer.java) Class

### Comments 

Comment:
1. Use advantages of Object Orientation Programing
    - Use PlayerState, SharedState classes to express state instead of getting string expression by your own
        - NO : [DisplayState with strings](https://gitlab.cecs.anu.edu.au/u7269158/comp1110-ass2-tue12i/-/blob/master/src/comp1110/ass2/gui/Viewer.java#L57-58)
          - String center = state[0].substring(state[0].indexOf("C") + 1, state[0].indexOf("B"));
        - YES : 
          - SharedState ss = new SharedState(state[0], DEFAULT_MAX_PLAYER);
          - String center = ss.center.getStateString();
    - Reasons for requiring this : 
        - Unified standard and easy to review code
        - Scalable for further tasks 12, 14, 16
        - Saves your time
 
2. Parametrize positions
    - Make reference coordinate of each boards
        - player board A, B, factories, center, discard, bag
    - Give each parameter name. 
        - e.g. CENTER_POS_X, CENTER_POS_Y
    - Store necessary parameters in interface [Constants](https://gitlab.cecs.anu.edu.au/u7269158/comp1110-ass2-tue12i/-/blob/master/src/comp1110/ass2/Constants.java)
        - NO : [Numbers without meaning](https://gitlab.cecs.anu.edu.au/u7269158/comp1110-ass2-tue12i/-/blob/master/src/comp1110/ass2/gui/Viewer.java#L63-87)
            - A.setFitWidth(16);
            - It is very hard interpret what '16' refers to
        - YES : 
            - A.setFitWidth(CENTER_WIDTH);
            - This CENTER_WIDTH is just an example
   - Reasons for requiring this :
       - Unified standard and easy to review code
       - Easy to fix and maintain codes (codes changes)
    
3. Specific structure of classes are needed
    - Only one [Viewer](https://gitlab.cecs.anu.edu.au/u7269158/comp1110-ass2-tue12i/-/blob/master/src/comp1110/ass2/gui/Viewer.java) class is not enough
    - You can split several structures of classes that only concern few or one part
        - e.g. Bag class of shared state only concerns about status of bag
   - Reasons for requiring this :
       - Unified standard and easy to review code
       - Easy to fix and maintain codes (codes changes)
       - Easy to allocate works 


