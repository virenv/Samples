# Test out the image comparison stuff that we would like to do

@objects
	
	LoginButton css #welcome-page > p:nth-child(4) > button
	
= Test out the image comparison results of Login button =
	
	@on desktop
		LoginButton:
					image file images/ModifiedButtonImage.png, error 1%