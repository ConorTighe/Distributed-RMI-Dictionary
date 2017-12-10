
<div class="container">
	<H1>Welcome ${name}</H1>

	<p>
		Enter a word to send to the server below!
	</p>
	<form action="SearchMenu" method="post">
    	word:<input type="text" name="word" placeholder="type the word here!"/>
    	<input type="submit" value="ok"/>
	</form>

	<p>
		<font color="red">${errorMessage}</font>
	</p>
</div>