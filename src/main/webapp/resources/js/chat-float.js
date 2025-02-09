const chatFloat = document.querySelector("#chat_float");
const btnCloseChatFloat = document.querySelector("#close_float_chat");

function openChatFloat() {
    chatFloat.style.display = 'flex';
}

btnCloseChatFloat.addEventListener('click', () => chatFloat.style.display = 'none');