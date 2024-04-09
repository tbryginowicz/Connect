async function getPosts() {
    const postLimit = document.getElementById('postLimit').value; // Pobierz wartość limitu postów
    const response = await fetch(`/posts?limit=${postLimit}`); // Wykonaj zapytanie GET na endpoint '/posts' z podanym limitem
    const posts = await response.json(); // Pobierz odpowiedź jako JSON
    displayPosts(posts); // Wywołaj funkcję do wyświetlania postów
}

// Funkcja do wyszukiwania postów po długości treści
async function searchByLength() {
    const minChars = document.getElementById('minChars').value;
    const maxChars = document.getElementById('maxChars').value;
    const response = await fetch(`/posts/byLength?minBodyLength=${minChars}&maxBodyLength=${maxChars}`);
    const posts = await response.json();
    displayPosts(posts);
}

// Funkcja do wyszukiwania postów po frazie w tytule
async function searchByTitleLikePost() {
    const keyword = document.getElementById('keyword').value;
    const response = await fetch(`/posts/byTitleLike?key=${keyword}`);
    const posts = await response.json();
    displayPosts(posts);
}

// Funkcja do wyszukiwania postów po frazie w treści
async function searchByBodyLikePost() {
    const keyword = document.getElementById('keywordBody').value;
    const response = await fetch(`/posts/byBodyLike?key=${keyword}`);
    const posts = await response.json();
    displayPosts(posts);
}

// Funkcja do wyświetlania postów w tabeli
function displayPosts(posts) {
    const postBody = document.getElementById('postBody');
    postBody.innerHTML = ''; // Wyczyść zawartość tabeli

    posts.forEach(post => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${post.userId}</td>
            <td>${post.id}</td>
            <td>${post.title}</td>
            <td>${post.body}</td>
            <td><button onclick="viewComments(${post.id})">Comments</button></td>
        `;
        postBody.appendChild(row);
    });
}

// Funkcja do pobierania komentarzy dla danego postu
async function viewComments(postId) {
    try {
        const response = await fetch(`/comments/byPostId?postId=${postId}`);
        if (response.status === 200) {
            const comments = await response.json();
            console.log('Comments for post ' + postId + ':', comments);
            // Tutaj możesz dodać logikę wyświetlającą komentarze w jakiejś sekcji na stronie
        } else {
            throw new Error('Failed to fetch comments. Status code: ' + response.status);
        }
    } catch (error) {
        console.error('Error fetching comments:', error.message);
    }
}
async function createPost() {
    const userId = document.getElementById('newPostUserId').value;
    const title = document.getElementById('newPostTitle').value;
    const body = document.getElementById('newPostBody').value;

    try {
        const response = await fetch(`/posts`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                userId: userId,
                title: title,
                body: body
            })
        });

        if (response.status === 201) {
            const newPost = await response.json();
            alert('New post created with ID: ' + newPost.id);
            // Opcjonalnie: odśwież listę postów
            getPosts();
        } else {
            throw new Error('Failed to create post. Status code: ' + response.status);
        }
    } catch (error) {
        console.error('Error creating post:', error.message);
        alert('Failed to create post. Please try again.');
    }
}
//ALBUMY
async function getAlbums() {
    const albumLimit = document.getElementById('albumLimit').value;
    const response = await fetch(`/albums?limit=${albumLimit}`);
    const albums = await response.json();
    displayAlbums(albums);
}

// Funkcja do wyświetlania albumów w tabeli
function displayAlbums(albums) {
    const albumBody = document.getElementById('albumBody');
    albumBody.innerHTML = ''; // Wyczyść zawartość tabeli

    albums.forEach(album => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${album.userId}</td>
            <td>${album.id}</td>
            <td>${album.title}</td>
            <td><button onclick="viewPhotos(${album.id})">View Photos</button></td>
        `;
        albumBody.appendChild(row);
    });
}
async function searchByTitleLike() {
    const keyword = document.getElementById('albumKeyword').value;
    const response = await fetch(`/albums/ByTitleLike?key=${keyword}`);
    const albums = await response.json();
    displayAlbums(albums);
}


// Funkcja do pobierania albumów po ID autora
async function searchByAuthorId() {
    const authorId = document.getElementById('authorId').value;
    const response = await fetch(`/albums/ByAuthorId?userId=${authorId}`);
    const albums = await response.json();
    displayAlbums(albums);
}

// Funkcja do tworzenia nowego albumu
async function createAlbum() {
    const userId = document.getElementById('newAlbumUserId').value;
    const title = document.getElementById('newAlbumTitle').value;

    const response = await fetch(`/albums`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            userId: userId,
            title: title
        })
    });

    const newAlbum = await response.json();
    alert('New album created with ID: ' + newAlbum.id);
}


async function getPhotos(albumId) {
    const response = await fetch(`http://localhost:8080/photos/byAlbumId?albumId=${albumId}`);
    const photos = await response.json();
    const photoList = document.getElementById("photoList");
    photoList.innerHTML = "";
    photos.forEach(photo => {
        const img = `<img src="${photo.thumbnailUrl}" alt="${photo.title}">`;
        photoList.innerHTML += img;
    });
}

// Funkcja do przekierowania na stronę z zdjęciami danego albumu
function viewPhotos(albumId) {
    window.location.href = `album.html?albumId=${albumId}`;
}

// Funkcja dla przycisku "Go Back"
function landingPage() {
    window.location.href = "index.html";
}

// Funkcja dla przycisku "New Post"
function newPostPage() {
    // Tutaj możesz dodać logikę, aby przekierować użytkownika na stronę tworzenia nowego posta
}

function browsePosts() {
    window.location.href = "posts.html";
}

function landingPage(){
    window.location.href = "index.html";
}

function browseAlbums() {
    window.location.href = "albums.html";
}
function newUser() {
    window.location.href = "newuser.html";
}


async function getCommentsForPost(postId) {
    try {
        const response = await fetch(`/comments/byPostId?postId=${postId}`);
        if (response.status === 200) {
            const comments = await response.json();
            displayComments(comments);
        } else {
            throw new Error('Failed to fetch comments. Status code: ' + response.status);
        }
    } catch (error) {
        console.error('Error fetching comments:', error.message);
    }
}

// Funkcja do wyświetlania komentarzy na stronie
function displayComments(comments) {
    const commentsContainer = document.getElementById('commentsContainer');
    commentsContainer.innerHTML = ''; // Wyczyść kontener na komentarze

    comments.forEach(comment => {
        const commentDiv = document.createElement('div');
        commentDiv.classList.add('comment');

        commentDiv.innerHTML = `
            <h3>${comment.name}</h3>
            <p>${comment.body}</p>
            <p><strong>Email:</strong> ${comment.email}</p>
        `;

        commentsContainer.appendChild(commentDiv);
    });
}
function viewComments(postId) {
    window.location.href = `comments.html?postId=${postId}`;
}
async function createAccount() {
    const name = document.getElementById('name').value;
    const username = document.getElementById('username').value;
    const email = document.getElementById('email').value;
    const phone = document.getElementById('phone').value;
    const website = document.getElementById('website').value;

    // Pobierz dane dotyczące adresu
    const street = document.getElementById('street').value;
    const city = document.getElementById('city').value;
    const zipcode = document.getElementById('zipcode').value;
    const address = { street, city, zipcode };

    // Pobierz dane dotyczące firmy
    const companyName = document.getElementById('companyName').value;
    const catchPhrase = document.getElementById('catchPhrase').value;
    const bs = document.getElementById('bs').value;
    const company = { name: companyName, catchPhrase, bs };

    const newUser = {
        name,
        username,
        email,
        phone,
        website,
        address,
        company
    };

    try {
        const response = await fetch('/users', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newUser)
        });

        if (response.status === 201) {
            const createdUser = await response.json();
            alert('New user created with ID: ' + createdUser.id);
            // Opcjonalnie: przekieruj użytkownika na inną stronę po rejestracji
        } else {
            throw new Error('Failed to create user. Status code: ' + response.status);
        }
    } catch (error) {
        console.error('Error creating user:', error.message);
        alert('Failed to create user. Please try again.');
    }
}
async function createAlbum() {
    const userId = document.getElementById('newAlbumUserId').value;
    const title = document.getElementById('newAlbumTitle').value;

    try {
        const response = await fetch(`/albums`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                userId: userId,
                title: title
            })
        });

        if (response.status === 201) {
            const newAlbum = await response.json();
            alert('New album created with ID: ' + newAlbum.id);
            // Opcjonalnie: odśwież listę albumów
            getAlbums();
        } else {
            throw new Error('Failed to create album. Status code: ' + response.status);
        }
    } catch (error) {
        console.error('Error creating album:', error.message);
        alert('Failed to create album. Please try again.');
    }
}