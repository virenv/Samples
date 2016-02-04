# Testing layout of a High chart object

@objects
	
	HighChartUiObject css #highcharts-0 > svg
	SeeCodeButton css #demo > div:nth-child(4) > div.span-one-third > button
	SeeUsGitButton css #about > div:nth-child(3) > div:nth-child(3) > p:nth-child(6) > a
	FullDocumentationButton css #about > div:nth-child(3) > div:nth-child(1) > p:nth-child(4) > a
	
= Testing dimensions of High chart object=
	@on desktop
	HighChartUiObject:
					height ~ 400 px
					width ~ 600 px
					near SeeCodeButton ~ 213px right
					near SeeUsGitButton ~ 203px bottom
				    near FullDocumentationButton ~ 180 px right, ~ 241 px bottom 
				    
				    
@objects

		TopIntroductionDiv css body > div.container > div

= Testing the top of the page =
	 @on desktop
	 TopIntroductionDiv:
	 				   height ~ 248 px
	 				   width ~  941 px 
	 				   inside screen ~ 59 px top, 25 to 38 px left
	 				   
	 HighChartUiObject:
	 					image file images/HighChartImage.png, error 1%