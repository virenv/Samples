# In this spec we will have multiple resolutions tests

@objects
	
	LoginButton css #welcome-page > p:nth-child(4) > button
	
= Test out the image comparison results of Login button =
	
	@on desktop 1366 X 786
		LoginButton:
					width ~ 78 px
		    		height ~ 46 px

	@on desktop 1280 X 720
		LoginButton:
					width ~ 70 px
		    		height ~ 40 px
		    		
	@on desktop 1280 X 768
		LoginButton:
					width ~ 8 px
		    		height ~ 6 px					