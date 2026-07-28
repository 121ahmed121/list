export default function LiveClassCard({ title, teacher, onJoin }) {
  return (
    <div className="bg-edy-blue rounded-[2rem] p-6 text-white card-shadow flex justify-between">
      <div>
        <div className="flex items-center gap-2 mb-2">
          <span className="w-2 h-2 bg-red-500 rounded-full animate-pulse"></span>
          <span className="text-[10px] font-bold uppercase">مباشر الآن</span>
        </div>
        <h4 className="font-bold text-lg">{title}</h4>
        <p className="text-xs text-white/70">{teacher}</p>
      </div>
      <button onClick={onJoin} className="bg-white text-edy-blue px-5 py-2 rounded-2xl text-xs font-black">
        انضم الآن
      </button>
    </div>
  );
}
