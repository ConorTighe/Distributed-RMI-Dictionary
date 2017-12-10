
<div class="container">
	<H1>Welcome ${name}</H1>


	<form action="ClientService" method="post">
    	word:<input type="text" id="fname" placeholder="type the word you want to search!"/>
    	<input type="submit" value="ok"/>
	</form>

	<p>
		<font color="red">${errorMessage}</font>
	</p>
	<a class="btn btn-success" href="/Client">Look up words on a server</a>
</div>