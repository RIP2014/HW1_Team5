(define (problem sokoban-2)
	(:domain sokoban-domain)
	(:objects bot block1 block2 A1 A2 A3 B1 B2 B3 B4 C1 C2 C3 D1 D2 E1 F1 LEFT RIGHT 		UP DOWN)
	(:init (BOT bot) (BLOCK block1) (BLOCK block2) 
		(LOC A1) (LOC A2) (LOC A3) (LOC B1) (LOC B2) (LOC B3) (LOC B4)
		(LOC C1) (LOC C2) (LOC C3) (LOC D1) (LOC D2) (LOC E1) (LOC F1)
		(At bot B1) (At block1 B2)(At block2 B3) 
		(Filled B1) (Filled B2) (Filled B3) 
		(NextTo A1 A2 RIGHT) (NextTo A2 A1 LEFT) (NextTo A2 A3 RIGHT) (NextTo A3 A2 LEFT) 
		(NextTo B1 B2 RIGHT) (NextTo B2 B1 LEFT) (NextTo B2 B3 RIGHT)
		(NextTo B3 B2 LEFT) (NextTo B3 B4 RIGHT) (NextTo B4 B3 LEFT)
		(NextTo C1 C2 RIGHT) (NextTo C2 C1 LEFT) (NextTo C2 C3 RIGHT) (NextTo C3 C2 LEFT)
		(NextTo D1 D2 RIGHT) (NextTo D2 D1 LEFT)
		(NextTo A1 B1 DOWN) (NextTo A2 B2 DOWN) (NextTo A3 B3 DOWN)
 		(NextTo B1 A1 UP) (NextTo B2 A2 UP) (NextTo B3 A3 UP)
		(NextTo B2 C1 DOWN) (NextTo B3 C2 DOWN) (NextTo B4 C3 DOWN)
 		(NextTo C1 B2 UP) (NextTo C2 B3 UP) (NextTo C3 B4 UP)
		(NextTo C2 D1 DOWN) (NextTo C3 D2 DOWN)
	 	(NextTo D1 C2 UP) (NextTo D2 C3 UP)
		(NextTo D2 E1 DOWN) (NextTo E1 F1 DOWN)
		(NextTo F1 E1 UP) (NextTo E1 D2 UP))

	(:goal (and (At block1 A1) (At block2 F1))))
