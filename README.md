# SudokuSolving

This repository shows the steps by Joseph DiMartino, Nicole Scott, and Daniel Jaffe took to implement the research from the article below. This research by Tirsa Ninia Lina and Matheus Supriyanto Rumetna shows how the solution of a sudoku puzzle can be found by implementing a Breadth First Search and a Depth Limited Search. We compared the two with how much time it would take to reach a solution. What we proved is that BFS will gather all possible answers while DLS will get a solution to the puzzle faster in all test cases. Our program dug deeper into the research by iterating to nodes with the most empty boxes in its row/column rather than iterating row-wise, improving search time and reducing memory taken up during the BFS.


https://www.researchgate.net/publication/358642884_Comparison_Analysis_of_Breadth_First_Search_and_Depth_Limited_Search_Algorithms_in_Sudoku_Game

