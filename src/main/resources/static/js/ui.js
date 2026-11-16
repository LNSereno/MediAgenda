(() => {
  const paths = {
    calendar: '<rect x="3" y="5" width="18" height="16" rx="3"/><path d="M16 3v4M8 3v4M3 11h18m-12 4h.01M12 15h.01M16 15h.01"/>',
    users: '<path d="M16 21v-2a4 4 0 0 0-4-4H6a4 4 0 0 0-4 4v2m20 0v-2a4 4 0 0 0-3-3.87M16 3a4 4 0 0 1 0 8"/><circle cx="9" cy="7" r="4"/>',
    heart: '<path d="M20.8 4.6a5.5 5.5 0 0 0-7.8 0L12 5.7l-1.1-1.1a5.5 5.5 0 0 0-7.8 7.8L12 21l8.8-8.6a5.5 5.5 0 0 0 0-7.8Z"/><path d="M3 12h5l2-4 4 8 2-4h5"/>',
    plus: '<path d="M12 5v14M5 12h14"/>',
    arrow: '<path d="M5 12h14m-6-6 6 6-6 6"/>',
    check: '<path d="m5 12 4 4L19 6"/>',
    mail: '<rect x="3" y="5" width="18" height="14" rx="3"/><path d="m3 6 9 7 9-7"/>',
    phone: '<path d="M22 16.9v3a2 2 0 0 1-2.2 2 19.8 19.8 0 0 1-8.6-3.1 19.5 19.5 0 0 1-6-6A19.8 19.8 0 0 1 2.1 4.2 2 2 0 0 1 4.1 2h3a2 2 0 0 1 2 1.7c.1 1 .4 1.9.7 2.8a2 2 0 0 1-.5 2.1L8 9.9a16 16 0 0 0 6 6l1.3-1.3a2 2 0 0 1 2.1-.5c.9.3 1.8.6 2.8.7a2 2 0 0 1 1.8 2.1Z"/>',
    file: '<path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8Zm0 0v6h6M8 13h8M8 17h5"/>',
    search: '<circle cx="10.5" cy="10.5" r="7.5"/><path d="m16 16 5 5"/>',
    clock: '<circle cx="12" cy="12" r="9"/><path d="M12 7v5l3 2"/>'
  };
  function icon(name) {
    const span = document.createElement('span');
    span.className = 'ui-icon';
    span.setAttribute('aria-hidden', 'true');
    span.innerHTML = '<svg width="19" height="19" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.7" stroke-linecap="round" stroke-linejoin="round">' + (paths[name] || paths.file) + '</svg>';
    return span;
  }
  document.querySelectorAll('.header-nav a').forEach((a, i) => a.prepend(icon(['calendar','users','heart'][i])));
  document.querySelectorAll('.stat').forEach((card, i) => { const badge = icon(['calendar','users','heart'][i]); badge.classList.add('stat-icon'); card.prepend(badge); const caption = document.createElement('span'); caption.className='stat-caption'; caption.textContent=['Sua agenda em um só lugar','Cuidado que cria conexões','Profissionais que fazem a diferença'][i]; card.append(caption); });
  document.querySelectorAll('.section-heading h2').forEach((h, i) => h.prepend(icon(['calendar','users','heart'][i])));
  document.querySelectorAll('.empty-state').forEach((state, i) => state.prepend(icon(['calendar','users','heart'][i])));
  document.querySelectorAll('.primary-link, .section-heading a').forEach(a => a.prepend(icon(a.textContent.includes('prontuário') ? 'file' : 'plus')));
  document.querySelectorAll('button').forEach(b => b.prepend(icon(b.classList.contains('cancel') ? 'calendar' : 'check')));
  document.querySelectorAll('label').forEach(label => { const key=label.htmlFor.toLowerCase(); label.prepend(icon(key.includes('email')?'mail':key.includes('phone')?'phone':key.includes('date')||key.includes('scheduled')?'calendar':key==='name'?'users':'file')); });
  document.querySelectorAll('.doctor-list li').forEach(li => li.prepend(icon('heart')));
  const welcome=document.querySelector('.welcome-panel');
  if(welcome){const date=document.createElement('p');date.className='today-label';date.append(icon('calendar'),document.createTextNode(new Intl.DateTimeFormat('pt-BR',{weekday:'long',day:'numeric',month:'long'}).format(new Date()))); welcome.prepend(date); const decor=document.createElement('div');decor.className='hero-art';decor.setAttribute('aria-hidden','true');decor.append(icon('heart'));welcome.append(decor);}
  document.querySelectorAll('.table-scroll').forEach((table,i)=>{
    const toolbar=document.createElement('div');toolbar.className='table-toolbar';const input=document.createElement('input');input.type='search';input.placeholder=i===0?'Buscar na agenda…':'Buscar paciente…';input.setAttribute('aria-label',input.placeholder.replace('…',''));toolbar.append(icon('search'),input);table.before(toolbar);
    const empty=document.createElement('p');empty.className='search-empty';empty.textContent='Nenhum resultado encontrado.';empty.hidden=true;table.after(empty);
    input.addEventListener('input',()=>{const query=input.value.trim().toLocaleLowerCase('pt-BR');let visible=0;table.querySelectorAll('tbody tr').forEach(row=>{row.hidden=!row.textContent.toLocaleLowerCase('pt-BR').includes(query);if(!row.hidden)visible++;});empty.hidden=visible>0;});
  });
  const motion=window.matchMedia('(prefers-reduced-motion: reduce)');
  if(!motion.matches){document.querySelectorAll('.stat strong').forEach(el=>{const end=Number(el.textContent);if(!Number.isFinite(end)||end===0)return;const start=performance.now();function tick(now){const t=Math.min((now-start)/850,1);el.textContent=Math.round(end*(1-Math.pow(1-t,3)));if(t<1)requestAnimationFrame(tick);}requestAnimationFrame(tick);});}
  if('IntersectionObserver' in window){const observer=new IntersectionObserver(entries=>{entries.forEach(entry=>{if(entry.isIntersecting){entry.target.classList.add('in-view');observer.unobserve(entry.target);}});},{threshold:.08});document.querySelectorAll('.dashboard-section').forEach(el=>{el.classList.add('reveal-ready');observer.observe(el);});
  const links=document.querySelectorAll('.header-nav a');const sections=new IntersectionObserver(entries=>entries.forEach(entry=>{if(entry.isIntersecting)links.forEach(a=>{const active=a.hash==='#'+entry.target.id;a.classList.toggle('active',active);if(active)a.setAttribute('aria-current','location');else a.removeAttribute('aria-current');});}),{rootMargin:'-15% 0px -60% 0px'});document.querySelectorAll('.dashboard-section').forEach(el=>sections.observe(el));}
})();