{
    printf("s+@NOTE@+PLEASE DO NOT EDIT THIS FILE. Edit index0.html instead.+\n");

	left = $4 / 1000;
	right = $4 % 1000;
    printf("s+@PDFS@+%d,%3.3d+\n", left, right);

	left = $7 / 1000;
	right = $7 % 1000;
    printf("s+@PAGES@+%d,%3.3d+\n", left, right);
}