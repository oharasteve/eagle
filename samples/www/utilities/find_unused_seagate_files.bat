for /r "I:\www2\Dox" %%i in ( * ) do @echo @if not exist %%i @move %%i I:\junk
