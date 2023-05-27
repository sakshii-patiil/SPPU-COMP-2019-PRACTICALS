function clearInput() {
  document.getElementById('input').value = '';
  document.getElementById('output').value = '';
}

function deleteCharacter() {
  var input = document.getElementById('input').value;
  document.getElementById('input').value = input.slice(0, -1);
}

function appendInput(value) {
  document.getElementById('input').value += value;
}

function calculate() {
  var input = document.getElementById('input').value;
  
  if (input === '') {
    alert('Please enter a valid expression!');
    return;
  }
  
  try {
    var result = eval(input);
    document.getElementById('output').value = result;
  } catch (error) {
    alert('Invalid expression!');
  }
}
