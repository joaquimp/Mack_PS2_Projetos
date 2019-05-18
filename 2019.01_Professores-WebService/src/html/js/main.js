// Get Input Elements
const idInput       = document.getElementById('idInput');
const nameInput     = document.getElementById('nameInput');
const registryInput = document.getElementById('registryInput');
const idUpdateInput       = document.getElementById('idUpdateInput');
const nameUpdateInput     = document.getElementById('nameUpdateInput');
const registryUpdateInput = document.getElementById('registryUpdateInput');

// Get Content Elements
const loggerElement = document.getElementById('logger');
const teachersElement = document.getElementById('professores');
const teacherSelectedElement = document.getElementById('teacherSelected');

// Store fetched teachers
var teachers = [];
var teacherSelected = null;

// =========================
// UTIL Methods
// =========================

var LogEnum = {
  INFO: "table-primary",
  WARNING: "table-warning",
  DANGER: "table-danger",
};

function addLog(msg, logEnum) {
  loggerElement.innerHTML = `<tr class="${logEnum}"><th scope="row">${new Date().toUTCString()}</th><td>${msg}</td>` + loggerElement.innerHTML;
}

function reloadTeachers() {
  var listaProfs = '';
  var n = teachers.length;
  for (var i = 0; i < n; i++) {
    listaProfs += makeTeacherRow(teachers[i]);
  }
  teachersElement.innerHTML = listaProfs;
}

function makeTeacherRow(professor) {
  return `<tr><th scope="row">${professor.id}</th>
          <td>${professor.nome}</td><td>${professor.matricula}</td>
          <td class="text-center">
            <button type="button" class="btn btn-warning" onClick="prepareUpdateForm(${professor.id})" data-toggle="modal" data-target="#updateModal">editar</button>
            <button type="button" class="btn btn-danger" onClick="prepareForDelete(${professor.id})" data-toggle="modal" data-target="#deleteModal">apagar</button>
          </td>`;
}

function resetCreateForm() {
  idInput.value = '';
  nameInput.value = '';
  registryInput.value = '';
}

function resetUpdateForm() {
  idUpdateInput.value = '';
  nameUpdateInput.value = '';
  registryUpdateInput.value = '';
  teacherSelected = null;
}

function resetDeleteModal() {
  deleteTeacher.innerHTML = "";
  teacherSelected = null;
}


// =========================
// CREATE Methods
// =========================
async function createTeacher() {
  const URL = `/api/professores`;
  const teacherData = {
    'nome': nameInput.value,
    'matricula': registryInput.value
  };
  const postRequest = {
    method: 'POST',
    body: JSON.stringify(teacherData),
    headers: {
      'Content-type': 'application/json;charset=UTF-8'
    }
  };
  try {
    const resp = await fetch(URL, postRequest);
    if (resp.status == 200) {
      addLog(`Professor ${teacherData.nome} criado com sucesso`, LogEnum.INFO);
      await resetCreateForm();
      await readTeachers();
    } else {
      addLog(`Criar Professor: respota diferente de 200 - ${resp.status}`, LogEnum.WARNING);
      await readTeachers();
    }
  } catch (e) {
    addLog(`Exception during "createTeacher()" - ${e}`, LogEnum.DANGER);
  }
}

// =========================
// READ Methods
// =========================
async function readTeachers() {
  teachersElement.innerHTML = 'carregando...';
  const url = `/api/professores`;
  try {
    const resp = await fetch(url);
    teachers = await resp.json();
    addLog('Professores carregados com sucesso',LogEnum.INFO);
    await reloadTeachers();
  } catch (e) {
    addLog(`Exception during "readTeachers()" - ${e}`, LogEnum.DANGER);
  }
}


// =========================
// UPDATE Methods
// =========================
function prepareUpdateForm(id) {
  for(i in teachers) {
    if (teachers[i].id == id) {
      teacherSelected = teachers[i];
      idUpdateInput.value = teacherSelected.id;
      nameUpdateInput.value = teacherSelected.nome;
      registryUpdateInput.value = teacherSelected.matricula;
      return;
    }
  }
  teacherSelected = null;
}

async function updateTeacher() {
  if(!teacherSelected) {
    addLog(`Não é possível atualizar o professor. Primeiro vocês deve selecione um professor!`, LogEnum.WARNING);
    return;
  }

  const URL = `/api/professores/${teacherSelected.id}`;
  const teacherData = {
    'nome': nameUpdateInput.value,
    'matricula': registryUpdateInput.value
  };
  const putRequest = {
    method: 'PUT',
    body: JSON.stringify(teacherData),
    headers: {
      'Content-type': 'application/json;charset=UTF-8'
    }
  };
  try {
    const resp = await fetch(URL, putRequest);
    if (resp.status == 200) {
      addLog(`Professor ${teacherData.nome} atualizado com sucesso`, LogEnum.INFO);
      resetUpdateForm();
      await readTeachers();
    }
  } catch (e) {
    addLog(`Exception during "updateTeacher()" - ${e}`, LogEnum.DANGER);
  }
}

// =========================
// DELETE Methods
// =========================
function prepareForDelete(id) {
  for(i in teachers) {
    if (teachers[i].id == id) {
      teacherSelected = teachers[i];
      teacherSelectedElement.innerHTML = teacherSelected.nome;
      return;
    }
  }
  teacherSelected = null;
}

async function deleteTeacher() {
  if(!teacherSelected) {
    addLog(`Não é possível apagar o professor. Primeiro vocês deve selecione um professor!`, LogEnum.WARNING);
    return;
  }
  const id = teacherSelected.id;
  teacherSelected = null;
  const URL = `/api/professores/${id}`;
  const deleteRequest = {
    method: 'DELETE'
  };
  try {
    const resp = await fetch(URL, deleteRequest);
    if (resp.status == 200) {
      addLog(`Professor ${id} apagado com sucesso`, LogEnum.INFO);
      await readTeachers();
    } else {
      addLog(`Professor ${id} não encontrado`, LogEnum.WARNING);
    }
  } catch (e) {
    addLog(`Exception during "deleteTeacher()" - ${e}`, LogEnum.DANGER);
  }
}

// Load Teachers Table on init
readTeachers();