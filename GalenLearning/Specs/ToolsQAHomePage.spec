# This is a simple Galen specification file. We will use this to test out look and feel of Galen's test website

@objects

	LoginButton css #welcome-page > p:nth-child(4) > button
	LoginInfoPara css #welcome-page > p:nth-child(3)
	MainDivTestPage css #welcome-page
	
= Login button tests=
	= Layout of Login button with absolute dimensions=
		@on desktop
		    LoginButton:
		    			width ~ 78 px
		    			height ~ 46 px
	    			
	    @on mobile
		    LoginButton:
		    			width ~ 269 px
		    			height ~ 46 px
	    			
	= Layout of Login button with dimension ranges=
		@on desktop
		    LoginButton:
		    			width 77 to 79 px
		    			height 45 to 47 px
		@on mobile
		 	 LoginButton:
		    			width 265 to 79 px
		    			height 45 to 47 px 			
		 	 
	    			
	# approximation at pixel is +- 2 px
	= Layout of Login button with approximation=
		@on desktop
			LoginButton:
						width ~ 76 px
						height ~ 44 px
						
	#example of below and above
	= Checking the existence of layout button below or above some =
	   @on desktop
		   LoginButton:
		   				below LoginInfoPara ~ 16 px
		   				inside MainDivTestPage ~ 752 px right, ~ 63 px bottom
		           