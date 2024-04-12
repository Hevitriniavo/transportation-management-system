import React, { forwardRef } from "react";

interface FileInputProps {
  className?: string;
  name: string;
}

const FileInput = forwardRef<HTMLInputElement, FileInputProps>((props, ref) => {
  const { name, className = "" } = props;

  return (
    <input
      type="file"
      className={className}
      name={name}
      id={name}
      ref={ref}
    />
  );
});

export default FileInput;
