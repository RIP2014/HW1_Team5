(define (domain sokoban-domain)
	(:requirements :strips)
	(:predicates (BLOCK ?block) 
		(BOT ?bot) 
		(At ?block ?loc) 
		(NextTo ?locA ?locB ?direction) 
		(Filled ?loc) )
	(:action moveBot
		:parameters(?locA ?locB ?bot ?direction)
		:preconditions (and (BOT ?bot) 
			(NextTo ?locA ?locB ?direction) 
			(At ?bot ?locA) 
			(not (Filled ?locB)))
		:effect (and(Filled ?locB) 
			(At ?bot ?locB) 
			(not (Filled ?locA)) 
			(not (At ?bot ?locA))))
	(:action moveBlock
		:parameters (?locA ?locB ?locC ?bot ?block ?direction)

		:preconditions (and (BOT ?bot) 
			(BLOCK ? block) 
			(At ?bot ?locA) 
			(At ?block ?locB) 
			(not (Filled ?locC)) 
			(NextTo ?locA ?locB ?direction) 
			(NextTo ?locB ?locC ?direction) )

		:effect (and (not (Filled ?locA)) 
			(not (At ?bot ?locA)) 
			(not (At ?block ?locB)) 
			(At ?bot ?locB) (Filled ?locC) 
			(At ?block ?locC) ))
