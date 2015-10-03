@rem This is for videos in the originals folder
@rem have to pipe output to index.html

@dir %1 | awk -f genmovies.awk