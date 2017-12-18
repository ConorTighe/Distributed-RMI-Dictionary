
<div class="container">
	<H1>Welcome ${name}, enter a word to lookup below!</H1>

	<p>
		Enter a word to find it on the server!
	</p>
	<form action="SearchMenu" method="post">
    	word:<input type="text" name="word" placeholder="type the word here!"/>
    	<input type="submit" value="ok"/>
	</form>

	<p>
		<font color="red">${errorMessage}</font>
	</p>
</div>
<div class="container">
	<H1>Delete a word</H1>

	<p>
		Enter a word to delete it from the server!
	</p>
	<form action="SearchMenu" method="post">
    	word:<input type="text" name="delete" placeholder="type the word here to delete it!"/>
    	<input type="submit" value="ok"/>
	</form>

	<p>
		<font color="red">${errorMessage}</font>
	</p>
</div>