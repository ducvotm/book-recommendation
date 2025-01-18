// script.js

document.addEventListener('DOMContentLoaded', function() {
    const moodSelect = document.getElementById('mood');
    const getRecommendationsButton = document.getElementById('getBooks');
    const recommendationsDiv = document.getElementById('recommendations');

    getRecommendationsButton.addEventListener('click', async () => {
        const selectedMood = moodSelect.value;
        
        if (!selectedMood) {
            recommendationsDiv.innerHTML = '<p class="error">Please select a mood first!</p>';
            return;
        }

        try {
            recommendationsDiv.innerHTML = '<p class="loading">Finding books for your mood...</p>';
            
            const response = await fetch(`http://localhost:8080/recommendations?mood=${selectedMood}`, {
                method: 'GET',
                headers: {
                    'Accept': 'application/json'
                }
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            const data = await response.json();
            displayBooks(data.books);
        } catch (error) {
            recommendationsDiv.innerHTML = `<p class="error">Error: ${error.message}</p>`;
        }
    });

    function displayBooks(books) {
        if (!books || books.length === 0) {
            recommendationsDiv.innerHTML = '<p>No books found for this mood.</p>';
            return;
        }

        const booksHTML = books.map(book => `
            <div class="book-card">
                <img src="${book.volumeInfo.imageLinks?.thumbnail || 'https://via.placeholder.com/150'}" alt="Book cover">
                <h3>${book.volumeInfo.title}</h3>
                <p class="author">By ${book.volumeInfo.authors ? book.volumeInfo.authors.join(', ') : 'Unknown'}</p>
                <p class="description">${book.volumeInfo.description || 'No description available.'}</p>
            </div>
        `).join('');

        recommendationsDiv.innerHTML = `
            <div class="book-list">
                ${booksHTML}
            </div>
        `;
    }
});