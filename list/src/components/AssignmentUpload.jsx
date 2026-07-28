export default function AssignmentUpload() {
  return (
    <label className="border-2 border-dashed border-edy-border rounded-2xl p-10 text-center bg-edy-sand/50 cursor-pointer hover:border-edy-green hover:bg-white transition-all">
      <input type="file" className="hidden" multiple />
      <div className="w-14 h-14 bg-white rounded-full flex items-center justify-center mx-auto mb-3 shadow-md">
        ⬆️
      </div>
      <p className="text-xs font-bold">اسحب الملفات هنا أو اضغط للاختيار</p>
      <p className="text-[10px] text-edy-textLight mt-1">PDF, DOCX</p>
    </label>
  );
}
