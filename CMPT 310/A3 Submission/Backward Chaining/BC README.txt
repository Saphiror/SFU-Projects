Backward Chaining README

Testcase files need to be in the same directory as the program file.
Testcase files need to only contain one set of rules in the format ( (rule) (rule2) (rule3)...)
The Testcase/Rule file is a parameter of the function. 
The query is a parameter as well and needs to be an actual atom in the rules. 
A quote should be used before the query to  create a literal expression and turn off the evaluator.
(bchain 'testcase.rkt' 'r)