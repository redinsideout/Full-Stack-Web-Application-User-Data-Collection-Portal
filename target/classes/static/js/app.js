async function requireAuth() {
  try {
    const res = await fetch('/api/auth/status', { credentials: 'include' });
    const data = await res.json();
    if (!data.authenticated) {
      window.location.href = '/index.html';
      return null;
    }
    return data.email;
  } catch {
    window.location.href = '/index.html';
    return null;
  }
}

async function logout() {
  try {
    await fetch('/api/auth/logout', { method: 'POST', credentials: 'include' });
  } catch (_) {}
  sessionStorage.clear();
  window.location.href = '/index.html';
}

function formatFileSize(bytes) {
  if (bytes < 1024) return bytes + ' B';
  if (bytes < 1024 * 1024) return (bytes / 1024).toFixed(1) + ' KB';
  return (bytes / (1024 * 1024)).toFixed(1) + ' MB';
}
