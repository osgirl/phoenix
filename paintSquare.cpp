// David Lanouette
// Coding problem 1.5 for Phoenix Integration

	Geometry* shape;
	Caret* caret;

	// if this is a square, paint it
	Square *sq = (Square *)shape;
	if (sq != 0)
	{
	   // get the current graphics caret position
	   double x = caret->x;
	   double y = caret->y;

	   // always draw with a solid brush; we’ll save the old one just in case
	   Brush* oldBrush = sq->getPaintBrush();
	   SolidBrush* sb = (SolidBrush *)oldBrush;
	   if (sb != 0)
	   {
		  // create a new solid brush with the default color
		  sq->setPaintBrush((Brush*)graphicsFactory.createObject(“SolidBrush”, SQUARE_COLOR));
	   }
	   // draw the sqaure
	   sq->draw((int)x, (int)y);
	   // restore the old paintbrush
	   sq->setPaintBrush(oldBrush);
	}
