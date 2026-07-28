export default function CourseCard({ image, category, title, progress }) {
  return (
    <div className="min-w-[260px] bg-white rounded-3xl p-4 card-shadow border border-edy-border/40">
      <div className="h-32 rounded-2xl mb-3 overflow-hidden">
        <img src={image} className="w-full h-full object-cover opacity-80" />
      </div>

      <span className="text-[9px] font-bold text-edy-blue bg-edy-blue/10 px-2 py-1 rounded-full uppercase">
        {category}
      </span>

      <h4 className="font-extrabold text-sm mt-2">{title}</h4>

      <div className="flex items-center justify-between mt-4">
        <div className="flex-1 bg-edy-sand h-1.5 rounded-full overflow-hidden mr-2">
          <div className="bg-edy-green h-full" style={{ width: `${progress}%` }} />
        </div>
        <span className="text-[10px] font-bold text-edy-textLight">{progress}%</span>
      </div>
    </div>
  );
}
